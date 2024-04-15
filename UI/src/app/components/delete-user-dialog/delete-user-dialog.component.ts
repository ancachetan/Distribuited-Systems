import {Component, Inject} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {UserService} from "../../services/UserService";

@Component({
    selector: 'app-delete-user-dialog',
    templateUrl: './delete-user-dialog.component.html',
    styleUrls: ['./delete-user-dialog.component.css']
})
export class DeleteUserDialogComponent {
    constructor(
        private dialogRef: MatDialogRef<DeleteUserDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private userService: UserService
    ) {
    }

    confirmDelete() {
        this.userService.delete(this.data.id).subscribe(() => {
            this.dialogRef.close(this.data.id);
        });
    }
}
