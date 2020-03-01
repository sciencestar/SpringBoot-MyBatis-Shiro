import com.domin.User;
import org.junit.Test;

public class CalPro<U extends User> {

    @Test
    public void cal(){
        int a=3,b=4;
        int result = this.add(a,b);
        System.out.println(result);
    }

    public int add(int a,int b){
        int result = a+b;
        return result;
    }
}
