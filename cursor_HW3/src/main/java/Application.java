import dbSelect.DbSelect;

public class Application {

    public static void main(String[] args) {
        DbSelect database = new DbSelect();

        System.out.println(database.getWorkers());
        System.out.println(database.getProductsByPrice(20, 50));
        System.out.println(database.getShopByStreet("danila"));
        System.out.println(database.getWorkerById(2));
        System.out.println(database.getProdByPriceAndType(30, "Vegetable"));

        database.closeConnection();
    }


}
