import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Workspace } from '../models/workspace.model';
import { environment } from '../../environments/environment';
import { TaskList } from '../models/task-list.model';
import { Task } from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  // Workspace endpoints

  createWorkspace(body: Workspace): Observable<Workspace> {
    return this.http.post<Workspace>(`${this.apiUrl}/api/workspaces`, body);
  }

  getWorkspaces(): Observable<Workspace[]> {
    return this.http.get<Workspace[]>(`${this.apiUrl}/api/workspaces`);
  }

  getWorkspaceById(id: number): Observable<Workspace> {
    return this.http.get<Workspace>(`${this.apiUrl}/api/workspaces/${id}`);
  }

  updateWorkspace(id: number, body: Workspace): Observable<Workspace> {
    return this.http.put<Workspace>(`${this.apiUrl}/api/workspaces/${id}`, body);
  }

  deleteWorkspace(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/api/workspaces/${id}`);
  }

  // TaskList endpoints

  createTaskList(body: TaskList): Observable<TaskList> {
    return this.http.post<TaskList>(`${this.apiUrl}/api/task-lists`, body);
  }

  getTaskListById(id: number): Observable<TaskList> {
    return this.http.get<TaskList>(`${this.apiUrl}/api/task-lists/${id}`);
  }

  updateTaskList(id: number, body: TaskList): Observable<TaskList> {
    return this.http.put<TaskList>(`${this.apiUrl}/api/task-lists/${id}`, body);
  }

  deleteTaskList(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/api/task-lists/${id}`);
  }

  getTaskListsByWorkspaceId(workspaceId: number): Observable<TaskList[]> {
    return this.http.get<TaskList[]>(`${this.apiUrl}/api/task-lists/workspace/${workspaceId}`);
  }

  // Task endpoints

  createTask(body: Task): Observable<Task> {
    return this.http.post<Task>(`${this.apiUrl}/api/tasks`, body);
  }

  getTaskById(id: number): Observable<Task> {
    return this.http.get<Task>(`${this.apiUrl}/api/tasks/${id}`);
  }

  updateTask(id: number, body: Task): Observable<Task> {
    return this.http.put<Task>(`${this.apiUrl}/api/tasks/${id}`, body);
  }

  deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/api/tasks/${id}`);
  }

  getTasksByTaskListId(taskListId: number): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiUrl}/api/tasks/task-list/${taskListId}`);
  }
}
