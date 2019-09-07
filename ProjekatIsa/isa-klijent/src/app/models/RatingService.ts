import { RentACar } from "./RentACar";
import { User } from './User';

export class RatingService{
    id : number;
    user : User;
    service : RentACar;
    rate : number;
}