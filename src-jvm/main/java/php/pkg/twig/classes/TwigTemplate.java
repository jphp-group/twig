package php.pkg.twig.classes;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import php.pkg.twig.TwigExtension;
import php.runtime.annotation.Reflection.Getter;
import php.runtime.annotation.Reflection.Namespace;
import php.runtime.annotation.Reflection.Signature;
import php.runtime.env.Environment;
import php.runtime.ext.core.classes.stream.Stream;
import php.runtime.ext.core.classes.util.WrapLocale;
import php.runtime.lang.BaseObject;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

@Namespace(TwigExtension.NS)
public class TwigTemplate extends BaseWrapper<PebbleTemplate> {

    public TwigTemplate(Environment env, PebbleTemplate wrappedObject) {
        super(env, wrappedObject);
    }

    public TwigTemplate(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Signature
    private void __construct() {
    }

    @Getter
    public String getName() {
        return getWrappedObject().getName();
    }

    @Signature
    public String render(Environment env) throws IOException {
        return render(env, new HashMap<>());
    }

    @Signature
    public String render(Environment env, Map<String, Object> contexts) throws IOException {
        StringWriter writer = new StringWriter();
        getWrappedObject().evaluate(writer, contexts, WrapLocale.getDefault(env));
        return writer.toString();
    }

    @Signature
    public String renderBlock(Environment env, String block) throws IOException {
        return renderBlock(env, block, new HashMap<>());
    }

    @Signature
    public String renderBlock(Environment env, String block, Map<String, Object> contexts) throws IOException {
        StringWriter writer = new StringWriter();
        getWrappedObject().evaluateBlock(block, writer, contexts, WrapLocale.getDefault(env));
        return writer.toString();
    }
}
