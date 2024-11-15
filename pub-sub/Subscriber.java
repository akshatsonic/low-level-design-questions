public abstract class Subscriber {
    protected String name;
    public Subscriber(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    abstract void onMessage(Message message);
}
