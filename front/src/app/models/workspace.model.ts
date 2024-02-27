import { WorkspaceVisibility } from "./enums/workspace-visibility.enum";

export interface Workspace {
  id: number;
  name: string;
  color: string;
  background: string;
  description: string;
  visibility: WorkspaceVisibility;
  userIds: number[];
}
