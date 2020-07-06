package msultont;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import java.util.List;

public class GetNodeLonFunction extends BrowserFunction {
    private List<Node> nodeLatLon;

    public GetNodeLonFunction(List<Node> node, Browser browser, String functionName) {
        super(browser, functionName);
        this.nodeLatLon = node;
    }

    @Override
    public Object function(Object[] arguments) {
        Object resultValue = null;

        System.out.println ("theJavaFunction() called from javascript with args:");
        for (Object arg : arguments) {
            if (arg == null) {
                System.out.println ("\t-->null");
            } else {
                resultValue = nodeLatLon.get((int)Double.parseDouble(arg.toString())).lon;
                System.out.println ("\t-->" + arg.getClass ().getName () + ": " + arg.toString ());
            }
        }
        return resultValue;
    }
}
