import { RentACar } from "./RentACar";
import { Filijale } from "./Filijale";
import { Category } from "./Category";


export class Car {
    id : BigInteger;
    name : string;
    regnumber : string;
    price : number;
    averageRating : number;
    rentalcars : RentACar;
    filijale : Filijale;
    category : Category;
    prodYear : number;
    

}