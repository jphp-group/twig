package php.pkg.twig.support;

import com.mitchellbosecke.pebble.attributes.AttributeResolver;
import com.mitchellbosecke.pebble.attributes.ResolvedAttribute;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.core.AttributeResolverExtension;
import com.mitchellbosecke.pebble.node.ArgumentsNode;
import com.mitchellbosecke.pebble.template.EvaluationContextImpl;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import php.runtime.Memory;
import php.runtime.env.Environment;
import php.runtime.exceptions.JPHPException;
import php.runtime.lang.IObject;
import php.runtime.lang.exception.BaseError;
import php.runtime.reflection.MethodEntity;

public class JPHPExtension extends AbstractExtension implements AttributeResolver {
    private Environment env;

    public JPHPExtension(Environment env) {
        this.env = env;
    }

    @Override
    public List<AttributeResolver> getAttributeResolver() {
        return Collections.singletonList(this);
    }

    private ResolvedAttribute resolve(IObject instance, String attributeName, Object[] argumentValues, String filename, int lineNumber) {
        return new ResolvedAttribute() {
            @Override
            public Object evaluate() {
                try {
                    if (argumentValues == null) {
                        Memory memory = env.getObjectProperty(instance, attributeName.toString());

                        if (memory.isNull() && !attributeName.startsWith("get")) {
                            String methodName = "get" + attributeName.toLowerCase();

                            MethodEntity method = instance.getReflection().findMethod(methodName);

                            if (method != null && !method.isAbstractable() && !method.isStatic()) {
                                return method.invokeDynamic(instance, env, env.trace());
                            }
                        }

                        return Memory.unwrap(env, memory);
                    } else {
                        return Memory.unwrap(env, instance.callMethodAny(env, attributeName, argumentValues));
                    }
                } catch (Throwable e) {
                    if (e instanceof BaseError) {
                        PebbleException exception = new PebbleException(
                                e,
                                ((BaseError)e).getMessage(env).toString(),
                                lineNumber,
                                filename
                        );

                        throw exception;
                    }

                    env.forwardThrow(e);
                    return null;
                }
            }
        };
    }

    private ResolvedAttribute resolve(Memory instance, String attributeName, Object[] argumentValues, String filename, int lineNumber) {
        if (instance.instanceOf(IObject.class)) {
            return resolve(instance.toObject(IObject.class), attributeName, argumentValues, filename, lineNumber);
        } else {
            if (argumentValues != null) {
                return null;
            }

            return new ResolvedAttribute() {
                @Override
                public Object evaluate() {
                    return Memory.unwrap(env, instance.valueOfIndex(attributeName));
                }
            };
        }
    }

    @Override
    public ResolvedAttribute resolve(Object instance, Object attributeNameValue,
                                     Object[] argumentValues, ArgumentsNode argumentsNode,
                                     EvaluationContextImpl evaluationContext, String filename, int lineNumber) {
        try {
            if (instance instanceof IObject) {
                return resolve((IObject) instance, attributeNameValue.toString(), argumentValues, filename, lineNumber);
            } else if (instance instanceof Memory) {
                return resolve((Memory) instance, attributeNameValue.toString(), argumentValues, filename, lineNumber);
            }
        } catch (Exception e) {
            if (e instanceof JPHPException) {
                throw new PebbleException(e.getCause(), e.getMessage(), lineNumber, filename);
            }

            throw e;
        }

        return null;
    }
}
