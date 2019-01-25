export class SignUpInfo {
    name: string;

    email: string;
    role: string[];
    password: string;
    lastname: string;
    city: string;
    phone: string;

    constructor(name: string, email: string, password: string,
                lastname:string, city:string, phone:string) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.city = city;
        this.phone = phone;
        this.role = ['ROLE_USER'];
    }
}
