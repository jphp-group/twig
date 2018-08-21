package php.pkg.twig.classes;

import com.mitchellbosecke.pebble.extension.escaper.SafeString;
import php.pkg.twig.TwigExtension;
import php.runtime.annotation.Reflection.Namespace;
import php.runtime.annotation.Reflection.Signature;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

@Namespace(TwigExtension.NS)
public class TwigSafeString extends BaseWrapper<SafeString> {
    public TwigSafeString(Environment env, SafeString wrappedObject) {
        super(env, wrappedObject);
    }

    public TwigSafeString(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Signature
    public void __construct(String str) {
        __wrappedObject = new SafeString(str);
    }

    @Signature
    public String __toString() {
        return getWrappedObject().toString();
    }
}
