package interceptor;

import model.Kweet;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Created by Nino Vrijman on 22-3-2017.
 */
@Interceptor
@TrendyKweet
public class TrendyKweetInterceptor {

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
        Object[] methodParameters = ic.getParameters();

        String originalContent = (String) methodParameters[0];
        String replacedContent = originalContent.replace("vet", "dik").replace("cool", "hard");

        methodParameters[0] = replacedContent;

        ic.setParameters(methodParameters);
        return ic.proceed();
    }
}
