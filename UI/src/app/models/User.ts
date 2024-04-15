import {Role} from "./Role";

export class User {
  id: string | undefined;
  name: string | undefined;
  username: string | undefined;
  password: string | undefined;
  role: Role | undefined;
}
