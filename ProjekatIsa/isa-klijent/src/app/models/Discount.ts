import { Car } from './Car'

export class Discount{
    id : number;
    dateTo : string;
    dateFrom : string;
    discount : number;
    car : Car = new Car();
}