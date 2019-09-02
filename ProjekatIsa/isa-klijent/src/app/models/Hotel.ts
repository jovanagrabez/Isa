import { AdditionalServiceForHotel } from './AdditionalServiceForHotel';


export class Hotel {
    id:number;
    name:string;
    address: string;
    description: string;
    rating : number;
    additional_services:Array<AdditionalServiceForHotel>;
    

 }
