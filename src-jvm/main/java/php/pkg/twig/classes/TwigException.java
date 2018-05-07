package php.pkg.twig.classes;

import com.mitchellbosecke.pebble.error.PebbleException;
import php.pkg.twig.TwigExtension;
import php.runtime.annotation.Reflection.Namespace;
import php.runtime.annotation.Reflection.Signature;
import php.runtime.env.Environment;
import php.runtime.ext.java.JavaException;
import php.runtime.reflection.ClassEntity;

@Namespace(TwigExtension.NS)
public class TwigException extends JavaException {
    public TwigException(Environment env, Throwable throwable) {
        super(env, throwable);
    }

    public TwigException(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Override
    public PebbleException getThrowable() {
        return (PebbleException) super.getThrowable();
    }

    @Signature
    public String getTemplateName() {
        return getThrowable().getFileName();
    }

    @Signature
    public Integer getTemplateLine() {
        return getThrowable().getLineNumber();
    }

    @Signature
    public String getTemplateMessage() {
        return getThrowable().getPebbleMessage();
    }
}
