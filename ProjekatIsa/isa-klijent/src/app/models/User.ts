import { Role } from "./Role";

export class User {
    id: number;
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    city: string;
    phoneNumber : string;
    verified: boolean; 
    roles : Array<Role>;   
    
}