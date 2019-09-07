import { User } from './User';
import { Room } from './Room';

export class RatingRoom{
    id : number;
    user : User;
    room : Room;
    rate : number;
}