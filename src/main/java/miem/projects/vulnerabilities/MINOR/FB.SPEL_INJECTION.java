package miem.projects.vulnerabilities.MINOR.FB;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class FB_SPEL_INJECTION {

    public Object unsafe(String userInput) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expr = parser.parseExpression(userInput); // здесь возможно RCE / data exfiltration
        return expr.getValue();
    }

    public Object safe(String userInput) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("input", userInput);

        Expression expr = parser.parseExpression("#input");
        return expr.getValue(context);
    }
}
