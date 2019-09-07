import { Room } from './Room';
import { User } from './User';

export class ReservationRoom {
    id : number;
    startDate : Date;
    endDate : Date;
    category : string;
    numPeople : number;
    totalPrice : number;
    room : Room;
    user : User;
    rateRoom : boolean;
    rateHotel : boolean;
    daysLeft : number;
}