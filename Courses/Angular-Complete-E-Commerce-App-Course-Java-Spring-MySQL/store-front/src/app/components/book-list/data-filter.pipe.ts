import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dataFilter'
})
export class DataFilterPipe implements PipeTransform {

  transform(array: any[], query: string): any {
    if (query) {
      return array.filter((row) => row.description.includes(query));
    }
    return array;
  }

}