package msultont;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import java.util.List;

public class GetNodeLatFunction extends BrowserFunction {

    private List<Node> nodeLatLon;

    public GetNodeLatFunction(List<Node> node, Browser browser, String functionName) {
        super(browser, functionName);
        this.nodeLatLon = node;
    }

    @Override
    public Object function(Object[] arguments) {
        Object resultValueLat = null;

        System.out.println ("theJavaFunction() called from javascript with args:");
        for (Object arg : arguments) {
            if (arg == null) {
                System.out.println ("\t-->null");
            } else {
                resultValueLat = nodeLatLon.get((int)Double.parseDouble(arg.toString())).lat;
                System.out.println ("\t-->" + arg.getClass ().getName () + ": " + arg.toString ());
            }
        }

        return resultValueLat;
    }
}
