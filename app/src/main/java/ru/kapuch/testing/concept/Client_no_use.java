package ru.kapuch.testing.concept;

public class Client_no_use {
    private String company_name;
    private String contact_name;
    private String phone_number;
    private String email;
    private String description;
    private String ltd;

    public static final Client_no_use[] CLIENT_NOUSES = new Client_no_use[]{
            new Client_no_use("ООО", "Концепт Технологии",
                    "Капустин Алексей Владимирович", "(495) 775-31-75",
                    "csc@c-tt.ru", ""),
            new Client_no_use("ЗАО", "Ребико", "Липин Мах Ефимович",
                    "(903)229-1885", "maxlipin74@c-tt.ru", "")

    };


    private Client_no_use(String ltd, String company_name, String contact_name, String phone_number, String email,
                         String description) {
        this.company_name = company_name;
        this.contact_name = contact_name;
        this.phone_number = phone_number;
        this.email = email;
        this.description = description;
        this.ltd = ltd;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getContact_name() {
        return contact_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

   /* public String getDescription() {
        return description;
    } */

    public String getLtd() {
        return ltd;
    }

    public String toString() {
        return this.company_name;
    }
}
