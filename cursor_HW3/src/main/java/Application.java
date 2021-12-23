import entity.Product;
import entity.Shop;
import entity.Worker;
import service.ProductService;
import service.ShopService;
import service.WorkerService;


public class Application {

    public static void main(String[] args) {

        ProductService productService = new ProductService();
        WorkerService workerService = new WorkerService();
        ShopService shopService = new ShopService();

        Product product = new Product(2, "Tomato", 45.00, 200, "Vegetable");
        Product product1 = new Product(12, "Potato", 45.00, 200, "Vegetable");
        Worker worker = new Worker(4, "Vova", "Zaba", 21, "vova@gmail.com");
        Worker worker1 = new Worker(4, "Marina", "Ship", 20, "marina@gmail.com");

        Shop shop = new Shop(2, "Ashan", "Kiev", "Okruznaya");

        System.out.println(shopService.getAll());

        System.out.println(shopService.getById(2));


    }
}
