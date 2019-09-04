import { RentACar } from "./RentACar";
import { Filijale } from "./Filijale";
import { Category } from "./Category";


export class Car {
    id : BigInteger;
    name : string;
    car_number : string;
    price : number;
    average_rating : number;
    prod_year : number;
    rentalcars : RentACar;
    filijale : Filijale;
    category : Category;
    
    

}