package com.ivo.component.thymleaf;

import com.ivo.component.thymleaf.attribute.SelectDictAttrProcessor;
import com.ivo.component.thymleaf.attribute.SelectListAttrProcessor;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author: wj
 * @Date: 2019-06-11 00:13
 * @Version 1.0
 */
public class TimoDialect extends AbstractProcessorDialect implements IExpressionObjectDialect {

    IDialect i;

    private static final String NAME = "TimoDialect";
    private static final String PREFIX = "mo";
    private IExpressionObjectFactory expressionObjectFactory = null;

    public TimoDialect() {
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        LinkedHashSet processors = new LinkedHashSet();
        processors.add(new SelectDictAttrProcessor(TemplateMode.HTML, dialectPrefix));
        processors.add(new SelectListAttrProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        if (this.expressionObjectFactory == null) {
            this.expressionObjectFactory = new TimoExpressionObjectFactory();
        }
        return this.expressionObjectFactory;
    }
}
