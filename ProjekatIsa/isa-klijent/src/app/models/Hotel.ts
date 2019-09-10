import { AdditionalServiceForHotel } from './AdditionalServiceForHotel';
import { Room } from './Room';


export class Hotel {
    id:number;
    name:string;
    city:string;
    address: string;
    description: string;
    rating : number;
    additional_services:Array<AdditionalServiceForHotel>;
    rooms:Array<Room>;
    rate : number;


 }
