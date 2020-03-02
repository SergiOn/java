import { Comment } from './comment';
import { User } from './User';

export class Photo {
  public photoId: number;
  public photoName: string;
  public title: string;
  public description: string;
  public user: User;
  public imageName: string;
  public likedByUserList: User[];
  public likes: number;
  public commentList: Comment[];
  public created: Date;
}
