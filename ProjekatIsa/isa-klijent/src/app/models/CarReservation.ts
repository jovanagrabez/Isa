import { Car } from './Car';
import { User } from './User';


export class CarReservation {
    id : number;
    startDate : Date;
    endDate : Date;
    pickupPlace : string;
    returnPlace : string;
    category : string;
    numPeople : number;
    numDays : number;
    totalPrice : number;
    dayRez : Date;
    user : User;
    car : Car;
    
    }