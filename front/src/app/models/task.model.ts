export interface Task {
    id: number;
    name: string;
    shortName: string;
    color: string;
    priorityColor: string;
    description: string;
    taskListId: number;
    ownerId: number;
    dateLastActivity: Date;
    start: Date;
    end: Date;
    closed: boolean;
}
