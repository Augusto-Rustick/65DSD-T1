import org.json.JSONArray;
import org.json.JSONObject;


public class test {
    public static void main(String[] args) {
        JSONObject myjson = new JSONObject("{'id':101, 'nome':'joão'}");
//        JSONArray the_json_array = myjson.getJSONArray();
        System.out.println(myjson.get("id"));
    }
}
