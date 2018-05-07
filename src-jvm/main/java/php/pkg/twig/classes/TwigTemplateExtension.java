package php.pkg.twig.classes;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import php.pkg.twig.TwigExtension;
import php.runtime.annotation.Reflection.Name;
import php.runtime.annotation.Reflection.Namespace;
import php.runtime.annotation.Reflection.Signature;
import php.runtime.env.Environment;
import php.runtime.invoke.Invoker;
import php.runtime.lang.BaseObject;
import php.runtime.reflection.ClassEntity;

@Name("TwigExtension")
@Namespace(TwigExtension.NS)
public class TwigTemplateExtension extends BaseObject {
    private Map<String, Filter> filterMap = new LinkedHashMap<>();

    public TwigTemplateExtension(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    public AbstractExtension fetchExtension() {
        return new AbstractExtension() {
            @Override
            public Map<String, Filter> getFilters() {
                return filterMap;
            }
        };
    }

    @Signature
    public void addFilter(Environment env, String name, Invoker filter) {
        filterMap.put(name, new Filter() {
            @Override
            public Object apply(Object o, Map<String, Object> map,
                                PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
                return filter.callAny(o, map, new TwigTemplate(env, pebbleTemplate), i);
            }

            @Override
            public List<String> getArgumentNames() {
                return null;
            }
        });
    }
}
