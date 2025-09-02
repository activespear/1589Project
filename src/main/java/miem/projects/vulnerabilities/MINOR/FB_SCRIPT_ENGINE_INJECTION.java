package miem.projects.vulnerabilities.MINOR.FB;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class FB_SCRIPT_ENGINE_INJECTION {

    public void unsafe(String userInput) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(userInput);
    }

    public void safe(Object safeValue) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        Bindings bindings = engine.createBindings();
        bindings.put("safeVar", safeValue);

        engine.eval("print('Safe variable value: ' + safeVar);", bindings);
    }
}
