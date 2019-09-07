import { User } from './User';
import { Hotel } from './Hotel';

export class RatingHotel{
    id : number;
    user : User;
    hotel : Hotel;
    rate : number;
}