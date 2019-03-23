package site.wzhe.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author: wangzhe.
 * @description:
 * @date: 2019/2/19.
 */
public class DeserializeTest {

    public static void main(String[] args) {
        String jsonStr = "[{\"name\":\"wangzhe\",\"age\":123}]";
        List<Person> persons = new Gson().fromJson(jsonStr, new TypeToken<List<Person>>(){}.getType());
        assert persons != null;

    }

    public static class Person {
        private String name;
    }
}
