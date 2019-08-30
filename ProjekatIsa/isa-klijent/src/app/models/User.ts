import { Role } from "./Role";

export class User {
    id: BigInteger;
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    city: string;
    phoneNumber : string;
    verified: boolean; 
    rolesDTO : Array<Role>;   
    
}