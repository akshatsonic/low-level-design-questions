import java.util.*;

/**
 * SOLID WORKBOOK + TUTORIAL  (LifeOS / Project Meteor: LLD Tracker -> "SOLID principles")
 *
 * HOW TO USE THIS FILE (about 35 minutes, ~7 min per principle):
 *   For each of the 5 parts:
 *     1. READ   the "RULE" and the "SMELL" (30 sec).
 *     2. STUDY  the worked example: BAD version, then the FIXED version (2 min).
 *     3. DO     the "YOUR TURN" exercise: refactor the bad class yourself in the
 *               "WRITE YOUR FIX BELOW" area. Do NOT scroll to a solution, there isn't one.
 *     4. EXPLAIN in the "MY EXAMPLE" comment: one place in YOUR real codebase where
 *               this principle was violated or followed (2 min).
 *
 *   Compile and run:   javac Solid.java && java Solid
 *
 * "DONE" MEANS: you can explain each principle out loud, without notes, using an example
 * from code you've actually worked on. If you finish only 3 or 4 principles, mark the sheet
 * row "Revisit", not "Done".
 *
 * HARD STOP: 11:30 AM. Deep Work Sprint 1 starts at 11:45.
 */
public class Solid {

    public static void main(String[] args) {
        System.out.println("=== 1. SRP ===");  Srp.demo();
        System.out.println("\n=== 2. OCP ===");  Ocp.demo();
        System.out.println("\n=== 3. LSP ===");  Lsp.demo();
        System.out.println("\n=== 4. ISP ===");  Isp.demo();
        System.out.println("\n=== 5. DIP ===");  Dip.demo();
        System.out.println("\nNow fill in the YOUR TURN sections and the MY EXAMPLE comments.");
    }

    // =====================================================================================
    // 1. SRP: Single Responsibility Principle
    // RULE : A class should have ONE reason to change.
    // SMELL: A class whose description needs the word "and" (calculates AND prints AND saves).
    //        If the DB team, the UI team and the finance team can each force an edit to the
    //        same class, it has three responsibilities.
    // =====================================================================================
    static class Srp {

        // ---- WORKED EXAMPLE: BAD ----
        static class BadInvoice {
            List<Double> items = new ArrayList<>();
            void add(double price) { items.add(price); }
            double total() { double t = 0; for (double p : items) t += p; return t; }
            void print()    { System.out.println("Invoice total: " + total()); }          // presentation concern
            void saveToDb() { System.out.println("INSERT invoice total=" + total()); }    // persistence concern
        }

        // ---- WORKED EXAMPLE: FIXED (one reason to change each) ----
        static class Invoice {                       // changes only if business math changes
            List<Double> items = new ArrayList<>();
            void add(double price) { items.add(price); }
            double total() { double t = 0; for (double p : items) t += p; return t; }
        }
        static class InvoicePrinter {                // changes only if the output format changes
            void print(Invoice i) { System.out.println("Invoice total: " + i.total()); }
        }
        static class InvoiceRepository {             // changes only if storage changes
            void save(Invoice i) { System.out.println("INSERT invoice total=" + i.total()); }
        }

        // ---- YOUR TURN ----
        // BadUserService has FOUR reasons to change. Split it so each class has one.
        // Hint: name the four jobs first (write them as a comment), then one class per job,
        // then a thin coordinator that calls them in order.
        static class BadUserService {
            void register(String email, String password) {
                if (email == null || !email.contains("@")) throw new IllegalArgumentException("bad email");
                String hash = new StringBuilder(password).reverse().toString();   // pretend hashing
                System.out.println("INSERT user " + email + " hash=" + hash);       // pretend DB
                System.out.println("Sending welcome email to " + email);            // pretend email
            }
        }

        // >>> WRITE YOUR FIX BELOW <<<
        // (the four jobs are: ________ , ________ , ________ , ________ )




        // MY EXAMPLE (a real class in my codebase with more than one reason to change):
        //


        static void demo() {
            BadInvoice bad = new BadInvoice(); bad.add(10); bad.add(5.5); bad.print();
            Invoice inv = new Invoice(); inv.add(10); inv.add(5.5);
            new InvoicePrinter().print(inv);
            new InvoiceRepository().save(inv);
            new BadUserService().register("a@b.com", "secret");
        }
    }

    // =====================================================================================
    // 2. OCP: Open/Closed Principle
    // RULE : Open for extension, closed for modification. Add new behavior by adding NEW code,
    //        not by editing code that already works.
    // SMELL: An if/else or switch on a "type" that you must edit every time a new type appears.
    // =====================================================================================
    static class Ocp {

        // ---- WORKED EXAMPLE: BAD (adding a triangle means editing this method) ----
        static class BadAreaCalculator {
            double area(String type, double a, double b) {
                if (type.equals("circle"))    return Math.PI * a * a;
                else if (type.equals("rect")) return a * b;
                else throw new IllegalArgumentException("unknown shape " + type);
            }
        }

        // ---- WORKED EXAMPLE: FIXED (adding a Triangle = a new class, zero edits here) ----
        interface Shape { double area(); }
        static class Circle implements Shape {
            final double r; Circle(double r) { this.r = r; }
            public double area() { return Math.PI * r * r; }
        }
        static class Rect implements Shape {
            final double w, h; Rect(double w, double h) { this.w = w; this.h = h; }
            public double area() { return w * h; }
        }
        static class AreaCalculator {
            double totalArea(List<Shape> shapes) { double t = 0; for (Shape s : shapes) t += s.area(); return t; }
        }

        // ---- YOUR TURN ----
        // A new customer type (e.g. "STUDENT", 15% off) should NOT require editing apply().
        // Redesign so adding STUDENT is a new class/entry only. Prove it: add STUDENT without
        // touching your calculator code.
        static class BadDiscount {
            double apply(String customerType, double amount) {
                switch (customerType) {
                    case "REGULAR": return amount;
                    case "PREMIUM": return amount * 0.90;
                    case "VIP":     return amount * 0.80;
                    default: throw new IllegalArgumentException("unknown " + customerType);
                }
            }
        }

        // >>> WRITE YOUR FIX BELOW <<<




        // MY EXAMPLE (a type-switch in my codebase that grows with every new case):
        //


        static void demo() {
            System.out.println("bad rect: " + new BadAreaCalculator().area("rect", 3, 4));
            List<Shape> shapes = Arrays.asList(new Circle(1), new Rect(3, 4));
            System.out.println("good total: " + new AreaCalculator().totalArea(shapes));
            System.out.println("bad VIP on 100: " + new BadDiscount().apply("VIP", 100));
        }
    }

    // =====================================================================================
    // 3. LSP: Liskov Substitution Principle
    // RULE : Anywhere a parent type is used, a subclass must work WITHOUT surprising the caller.
    //        A subclass must not strengthen preconditions, weaken results, or throw
    //        "not supported" for something the parent promised.
    // SMELL: Subclass overrides a method to throw UnsupportedOperationException, or callers
    //        need instanceof checks to avoid certain subclasses.
    // =====================================================================================
    static class Lsp {

        // ---- WORKED EXAMPLE: BAD (a Square "is-a" Rectangle in math, but not in behavior) ----
        static class BadRectangle {
            protected int w, h;
            void setWidth(int w)  { this.w = w; }
            void setHeight(int h) { this.h = h; }
            int area() { return w * h; }
        }
        static class BadSquare extends BadRectangle {
            @Override void setWidth(int w)  { this.w = w; this.h = w; }   // surprise: changes height too
            @Override void setHeight(int h) { this.w = h; this.h = h; }   // surprise: changes width too
        }
        // The caller's reasonable expectation: set 5 x 4 gives area 20.
        static int resizeTo5x4(BadRectangle r) { r.setWidth(5); r.setHeight(4); return r.area(); }

        // ---- WORKED EXAMPLE: FIXED (don't force an inheritance relationship that lies) ----
        interface Shape { int area(); }
        static class Rectangle implements Shape {
            final int w, h; Rectangle(int w, int h) { this.w = w; this.h = h; }
            public int area() { return w * h; }
        }
        static class Square implements Shape {
            final int side; Square(int side) { this.side = side; }
            public int area() { return side * side; }
        }

        // ---- YOUR TURN ----
        // Every Bird inherits fly(), but Penguin can't. Redesign the hierarchy so that no code
        // that holds a "thing that flies" can ever receive a Penguin, and the compiler enforces it.
        // Hint: the problem is that fly() lives too high in the hierarchy.
        static class BadBird {
            void eat() { System.out.println("eating"); }
            void fly() { System.out.println("flying"); }
        }
        static class BadPenguin extends BadBird {
            @Override void fly() { throw new UnsupportedOperationException("penguins can't fly"); }
        }

        // >>> WRITE YOUR FIX BELOW <<<




        // MY EXAMPLE (a subclass in my codebase that throws / ignores / narrows what the parent promised):
        //


        static void demo() {
            System.out.println("Rectangle 5x4 area: " + resizeTo5x4(new BadRectangle()));   // 20
            System.out.println("Square    5x4 area: " + resizeTo5x4(new BadSquare()));      // 16 <-- LSP broken
            Shape s = new Square(4);
            System.out.println("fixed square area: " + s.area());
            try { new BadPenguin().fly(); } catch (UnsupportedOperationException e) { System.out.println("BadPenguin: " + e.getMessage()); }
        }
    }

    // =====================================================================================
    // 4. ISP: Interface Segregation Principle
    // RULE : No class should be forced to implement methods it doesn't use. Prefer several
    //        small, focused interfaces over one fat one.
    // SMELL: Empty method bodies, or "throw new UnsupportedOperationException()" in an
    //        implementation just to satisfy an interface.
    // =====================================================================================
    static class Isp {

        // ---- WORKED EXAMPLE: BAD ----
        interface BadWorker { void work(); void eat(); }
        static class BadHuman implements BadWorker {
            public void work() { System.out.println("human working"); }
            public void eat()  { System.out.println("human eating"); }
        }
        static class BadRobot implements BadWorker {
            public void work() { System.out.println("robot working"); }
            public void eat()  { throw new UnsupportedOperationException("robots don't eat"); } // forced stub
        }

        // ---- WORKED EXAMPLE: FIXED ----
        interface Workable { void work(); }
        interface Eatable  { void eat(); }
        static class Human implements Workable, Eatable {
            public void work() { System.out.println("human working"); }
            public void eat()  { System.out.println("human eating"); }
        }
        static class Robot implements Workable {
            public void work() { System.out.println("robot working"); }
        }

        // ---- YOUR TURN ----
        // SimplePrinter only prints but is forced to implement scan() and fax().
        // Split MultiFunctionDevice so SimplePrinter implements only what it supports, while
        // OfficeMachine still supports all three.
        interface MultiFunctionDevice { void print(String doc); void scan(String doc); void fax(String doc); }
        static class SimplePrinter implements MultiFunctionDevice {
            public void print(String doc) { System.out.println("printing " + doc); }
            public void scan(String doc)  { throw new UnsupportedOperationException(); }
            public void fax(String doc)   { throw new UnsupportedOperationException(); }
        }

        // >>> WRITE YOUR FIX BELOW <<<




        // MY EXAMPLE (a fat interface in my codebase with methods some implementers can't honor):
        //


        static void demo() {
            new BadHuman().work();
            try { new BadRobot().eat(); } catch (UnsupportedOperationException e) { System.out.println("BadRobot: " + e.getMessage()); }
            new Human().eat();
            new Robot().work();
            new SimplePrinter().print("report.pdf");
        }
    }

    // =====================================================================================
    // 5. DIP: Dependency Inversion Principle
    // RULE : High-level policy shouldn't depend on low-level details. Both should depend on
    //        abstractions. In practice: depend on an interface and have the dependency handed
    //        in (constructor injection) instead of calling `new` inside.
    // SMELL: `new ConcreteThing()` inside a business class; impossible to unit test without the
    //        real database, network or email server.
    // =====================================================================================
    static class Dip {

        // ---- WORKED EXAMPLE: BAD ----
        static class MySqlOrderRepository { void save(String order) { System.out.println("MySQL save " + order); } }
        static class BadOrderService {
            private final MySqlOrderRepository repo = new MySqlOrderRepository();   // hard-wired
            void place(String order) { repo.save(order); }
        }

        // ---- WORKED EXAMPLE: FIXED ----
        interface OrderRepository { void save(String order); }
        static class MySqlRepo implements OrderRepository {
            public void save(String order) { System.out.println("MySQL save " + order); }
        }
        static class InMemoryRepo implements OrderRepository {                    // test double
            final List<String> saved = new ArrayList<>();
            public void save(String order) { saved.add(order); }
        }
        static class OrderService {
            private final OrderRepository repo;
            OrderService(OrderRepository repo) { this.repo = repo; }              // injected
            void place(String order) { repo.save(order); }
        }

        // ---- YOUR TURN ----
        // NotificationManager is hard-wired to email. Make it depend on an abstraction so that:
        //   (a) you can plug in SMS or push without editing NotificationManager, and
        //   (b) you can test it with a fake that records messages instead of sending.
        // Bonus: this also touches OCP. Notice how the principles overlap.
        static class EmailSender { void send(String to, String msg) { System.out.println("EMAIL to " + to + ": " + msg); } }
        static class BadNotificationManager {
            private final EmailSender sender = new EmailSender();
            void notifyUser(String user, String msg) { sender.send(user, msg); }
        }

        // >>> WRITE YOUR FIX BELOW <<<




        // MY EXAMPLE (a class in my codebase that calls `new` on a concrete dependency, or one that
        // takes an interface via constructor and why that helped testing):
        //


        static void demo() {
            new BadOrderService().place("order-1");
            InMemoryRepo fake = new InMemoryRepo();
            new OrderService(fake).place("order-2");
            System.out.println("fake recorded: " + fake.saved);
            new BadNotificationManager().notifyUser("akshat", "hello");
        }
    }

    // =====================================================================================
    // SELF-CHECK (answer out loud, no notes; tick when you can)
    // =====================================================================================
    // [ ] SRP: What are the "reasons to change" for the class I just split?
    // [ ] OCP: Which line would I have had to edit to add STUDENT in the BAD version? In mine?
    // [ ] LSP: Why does BadSquare break the caller even though it compiles?
    // [ ] ISP: What did SimplePrinter stop being forced to do?
    // [ ] DIP: How would I unit test NotificationManager without sending a real email?
    // [ ] Which two principles overlapped in my solutions, and why?
    //
    // SCORE: ___ / 6   (6 = mark "Done" in the sheet; 4-5 = "Revisit"; below 4 = redo tomorrow)
}