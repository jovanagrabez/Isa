import { RentACar } from "./RentACar";
import { User } from './User';

export class RatingService{
    id : number;
    user : User;
    car : RentACar;
    rate : number;
}