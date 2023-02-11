import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(payload.CoursePrice());
        // 1) Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);
        // 2)  Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);
        // 3) Print Title of the first course
        String titleFirstCourse = js.get("courses[2].title");
        System.out.println(titleFirstCourse);

        //4) Print All course titles and their respective Prices
        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            System.out.println(js.get("courses[" + i + "].price").toString());

            System.out.println(courseTitles);

        }

        //5)Print no of copies sold by RPA Course
        System.out.println("-------Print no of copies sold by RPA Course---------");
        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            if (courseTitles.equalsIgnoreCase("RPA")) {
                //copies sold
                int copies = js.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;

            }


        }
    }
}
