import { Role } from "./Role";

export class User {
    id: BigInteger;
    email: string;
    password: string;
    name: string;
    lastname: string;
    city: string;
    phone: string;
    verified: boolean; 
    rolesDTO : Array<Role>;   
    
}