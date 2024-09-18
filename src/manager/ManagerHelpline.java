package manager;
//manager helpline class
public class ManagerHelpline {
//    attributes
    String name;
    String queryy;
    public ManagerHelpline(String name,String queryy){
        this.name=name;
        this.queryy=queryy;
    }
//constructor and getters and setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getQueryy() {
        return queryy;
    }

    public void setQueryy(String queryy) {
        this.queryy = queryy;
    }
}
