import { User } from './User';
import { Car } from './Car';

export class RatingCar{
    id : number;
    user : User;
    car : Car;
    rate : number;
}