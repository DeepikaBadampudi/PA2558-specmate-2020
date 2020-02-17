import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class GraphicalEditorService {

    public initModel: EventEmitter<void> = new EventEmitter<void>();

    constructor() {
    }

    public initGraphicalModel(): void {
        this.initModel.emit();
    }
}
