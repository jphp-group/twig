package php.pkg.twig.classes;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.Function;
import com.mitchellbosecke.pebble.extension.Test;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import php.pkg.twig.TwigExtension;
import php.runtime.Memory;
import php.runtime.annotation.Reflection;
import php.runtime.annotation.Reflection.Arg;
import php.runtime.annotation.Reflection.Name;
import php.runtime.annotation.Reflection.Namespace;
import php.runtime.annotation.Reflection.Signature;
import php.runtime.common.HintType;
import php.runtime.env.Environment;
import php.runtime.invoke.Invoker;
import php.runtime.lang.BaseObject;
import php.runtime.memory.ArrayMemory;
import php.runtime.memory.support.operation.map.HashMapMemoryOperation;
import php.runtime.reflection.ClassEntity;

@Name("TwigExtension")
@Namespace(TwigExtension.NS)
public class TwigTemplateExtension extends BaseObject {
    private Map<String, Filter> filterMap = new LinkedHashMap<>();
    private Map<String, Function> functionMap = new LinkedHashMap<>();
    private Map<String, Test> testMap = new LinkedHashMap<>();
    private Map<String, Object> globalVarMap = new LinkedHashMap<>();

    private final HashMapMemoryOperation mapOperation = new HashMapMemoryOperation(String.class, Object.class);

    public TwigTemplateExtension(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    public AbstractExtension fetchExtension() {
        return new AbstractExtension() {
            @Override
            public Map<String, Filter> getFilters() {
                return filterMap;
            }

            @Override
            public Map<String, Function> getFunctions() {
                return functionMap;
            }

            @Override
            public Map<String, Object> getGlobalVariables() {
                return globalVarMap;
            }

            @Override
            public Map<String, Test> getTests() {
                return testMap;
            }
        };
    }

    private Memory convertArgs(Environment env, Map<String, Object> map) {
        try {
            return mapOperation.unconvert(env, env.trace(), map);
        } catch (Throwable throwable) {
            env.forwardThrow(throwable);
            return Memory.NULL;
        }
    }

    @Signature
    public void addFilter(Environment env, String name, Invoker filter) {
        addFilter(env, name, filter, new ArrayMemory());
    }

    @Signature
    public void addFilter(Environment env, String name, Invoker filter, @Arg(type = HintType.ARRAY) Memory argNames) {
        List<String> _argNames = Arrays.asList(argNames.toValue(ArrayMemory.class).toStringArray());

        filterMap.put(name, new Filter() {
            @Override
            public Object apply(Object o, Map<String, Object> map,
                                PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
                return filter.callAny(o, convertArgs(env, map), new TwigTemplate(env, pebbleTemplate), i);
            }

            @Override
            public List<String> getArgumentNames() {
                return _argNames;
            }
        });
    }

    @Signature
    public void addFunction(Environment env, String name, Invoker function) {
        addFunction(env, name, function, new ArrayMemory());
    }

    @Signature
    public void addFunction(Environment env, String name, Invoker function, @Arg(type = HintType.ARRAY) Memory argNames) {
        List<String> _argNames = Arrays.asList(argNames.toValue(ArrayMemory.class).toStringArray());

        functionMap.put(name, new Function() {
            @Override
            public Object execute(Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) {
                return function.callAny(convertArgs(env, map), new TwigTemplate(env, pebbleTemplate), i);
            }

            @Override
            public List<String> getArgumentNames() {
                return _argNames;
            }
        });
    }

    @Signature
    public void addTest(Environment env, String name, Invoker test) {
        addTest(env, name, test, new ArrayMemory());
    }

    @Signature
    public void addTest(Environment env, String name, Invoker test, @Arg(type = HintType.ARRAY) Memory argNames) {
        List<String> _argNames = Arrays.asList(argNames.toValue(ArrayMemory.class).toStringArray());

        testMap.put(name, new Test() {
            @Override
            public boolean apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
                return test.callAny(o, convertArgs(env, map), new TwigTemplate(env, pebbleTemplate), i).toBoolean();
            }

            @Override
            public List<String> getArgumentNames() {
                return _argNames;
            }
        });
    }

    @Signature
    public void addGlobalVar(String name, Memory value) {
        globalVarMap.put(name, value);
    }
}
