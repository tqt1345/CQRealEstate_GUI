package testing;
import com.mycompany.cqrealestate_gui.*;
public class TestData {
    public static void makeSampleData() {

        DataHandler.clearData();

        DataHandler.landList.add(new Land(1, 1, "1 Test Street", 100));
        DataHandler.landList.add(new Land(2, 2, "2 Test Street", 200));
        DataHandler.landList.add(new Land(3, 3, "3 Test Street", 300));

        DataHandler.houseAndLandList.add(new HouseAndLand(1, 1, "1 Test Street", 100, 50, 2, 1));
        DataHandler.houseAndLandList.add(new HouseAndLand(2, 2, "2 Test Street", 200, 100, 3, 2));
        DataHandler.houseAndLandList.add(new HouseAndLand(3, 3, "3 Test Street", 300, 150, 4, 3));

        DataHandler.buyerList.add(new Buyer(1, "Tarique", "Richard", "1 Test Street", "12345678"));
        DataHandler.buyerList.add(new Buyer(2, "Max", "Bob", "2 Test Street", "12345678"));
        DataHandler.buyerList.add(new Buyer(3, "Turnbull", "Amogus", "3 Test Street", "12345678"));

        DataHandler.sellerList.add(new Seller(1, "John", "Smith", "1 Test Street", "12345678"));
        DataHandler.sellerList.add(new Seller(2, "Jane", "Doe", "2 Test Street", "12345678"));
        DataHandler.sellerList.add(new Seller(3, "Max", "Rich", "3 Test Street", "12345678"));

        DataHandler.saveData();

    }
}
