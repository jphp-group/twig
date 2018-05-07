package php.pkg.twig.classes;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.PebbleEngine.Builder;
import com.mitchellbosecke.pebble.attributes.AttributeResolver;
import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.lexer.Syntax;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import com.mitchellbosecke.pebble.tokenParser.TokenParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import php.pkg.twig.TwigExtension;
import php.pkg.twig.support.JPHPExtension;
import php.runtime.Memory;
import php.runtime.annotation.Reflection.Arg;
import php.runtime.annotation.Reflection.Namespace;
import php.runtime.annotation.Reflection.Optional;
import php.runtime.annotation.Reflection.Signature;
import php.runtime.env.Environment;
import php.runtime.ext.core.classes.stream.Stream;
import php.runtime.lang.BaseObject;
import php.runtime.lang.IObject;
import php.runtime.memory.StringMemory;
import php.runtime.reflection.ClassEntity;

@Namespace(TwigExtension.NS)
public class TwigEngine extends BaseObject {
    private PebbleEngine engine;
    private Builder builder;
    private List<TwigTemplateExtension> extensions = new ArrayList<>();

    public TwigEngine(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Signature
    public void __construct(Environment env,
                            @Arg(typeClass = "twig\\TwigLoader") IObject loader,
                            @Optional("NULL") Memory options) {
        Builder builder = new Builder();
        Syntax.Builder syntaxBuilder = new Syntax.Builder();

        this.builder = builder.syntax(syntaxBuilder.build())
                .extension(new JPHPExtension(env))
                .loader(fetchLoader(env, loader))
                .cacheActive(options.valueOfIndex("cache").toBoolean())
                .strictVariables(options.valueOfIndex("strict").toBoolean());

        engine = this.builder.build();
    }

    @Signature
    public void addExtension(TwigTemplateExtension extension) {
        builder = this.builder.extension(extension.fetchExtension());
        engine = builder.build();
    }

    @Signature
    public TwigTemplate load(Environment env, String name) {
        return new TwigTemplate(env, engine.getTemplate(name));
    }

    @Signature
    public String render(Environment env, String name) throws IOException {
        return render(env, name, new HashMap<>());
    }

    @Signature
    public String render(Environment env, String name, Map<String, Object> context) throws IOException {
        return load(env, name).render(env, context);
    }

    public Reader getReader(String cacheKey, IObject loader) {
        Environment env = getEnvironment();
        try {
            return new InputStreamReader(Stream.getInputStream(
                    env, env.invokeMethod(loader, "read", StringMemory.valueOf(cacheKey))
            ));
        } catch (Throwable throwable) {
            env.forwardThrow(throwable);
            return null;
        }
    }

    public Loader<String> fetchLoader(Environment env, IObject loader) {
        return new Loader<String>() {
            @Override
            public Reader getReader(String cacheKey) {
                return TwigEngine.this.getReader(cacheKey, loader);
            }

            @Override
            public void setCharset(String charset) {
                loader.callMethodAny(env, "setCharset", charset);
            }

            @Override
            public void setPrefix(String prefix) {
                loader.callMethodAny(env, "setPrefix", prefix);
            }

            @Override
            public void setSuffix(String suffix) {
                loader.callMethodAny(env, "setSuffix", suffix);
            }

            @Override
            public String resolveRelativePath(String relativePath, String anchorPath) {
                return loader.callMethodAny(env, "resolveRelativePath", relativePath, anchorPath).toString();
            }

            @Override
            public String createCacheKey(String templateName) {
                return loader.callMethodAny(env, "createCacheKey", templateName).toString();
            }
        };
    }
}
