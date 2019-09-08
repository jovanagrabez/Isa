import { User } from './User';
import { Flight } from './Flight'


export class RatingFlight{
    id : number;
    user : User;
    flight : Flight;
    rate : number;
}