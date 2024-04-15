import {Component, Inject} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {DeviceService} from "../../services/DeviceService";

@Component({
    selector: 'app-delete-device-dialog',
    templateUrl: './delete-device-dialog.component.html',
    styleUrls: ['./delete-device-dialog.component.css']
})
export class DeleteDeviceDialogComponent {

    constructor(
        private dialogRef: MatDialogRef<DeleteDeviceDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private deviceService: DeviceService
    ) {
    }

    confirmDelete() {
        this.deviceService.delete(this.data.id).subscribe(() => {
            this.dialogRef.close(this.data.id);
        });
    }
}
