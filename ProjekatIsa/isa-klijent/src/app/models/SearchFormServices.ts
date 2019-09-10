import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SearchFormServices {
    city: string;
    name : string;
    nameHotel : string;
    startDate: Date;
    endDate: Date;
}