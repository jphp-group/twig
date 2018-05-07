package php.pkg.twig;

import com.mitchellbosecke.pebble.error.AttributeNotFoundException;
import com.mitchellbosecke.pebble.error.LoaderException;
import com.mitchellbosecke.pebble.error.ParserException;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.error.RootAttributeNotFoundException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import php.pkg.twig.classes.TwigEngine;
import php.pkg.twig.classes.TwigException;
import php.pkg.twig.classes.TwigTemplate;
import php.pkg.twig.classes.TwigTemplateExtension;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;

public class TwigExtension extends Extension {
    public static final String NS = "twig";
    
    @Override
    public Status getStatus() {
        return Status.EXPERIMENTAL;
    }
    
    @Override
    public void onRegister(CompileScope scope) {
        registerJavaException(scope, TwigException.class,
                PebbleException.class, ParserException.class, LoaderException.class,
                AttributeNotFoundException.class, RootAttributeNotFoundException.class
        );

        registerWrapperClass(scope, PebbleTemplate.class, TwigTemplate.class);
        registerClass(scope, TwigEngine.class);
        registerClass(scope, TwigTemplateExtension.class);
    }
}