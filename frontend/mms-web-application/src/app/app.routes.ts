import {Routes} from '@angular/router';
import {CustomerManagementComponent} from "./main-ui/customer-management/customer-management.component";
import {DashboardComponent} from "./main-ui/dashboard/dashboard.component";
import {DocumentManagementComponent} from "./main-ui/document-management/document-management.component";
import {IncomeManagementComponent} from "./main-ui/income-management/income-management.component";
import {ProjectManagementComponent} from "./main-ui/project-management/project-management.component";
import {SettingsComponent} from "./main-ui/settings/settings.component";
import {TodoManagementComponent} from "./main-ui/todo-management/todo-management.component";
import {WorkingHoursManagementComponent} from "./main-ui/working-hours-management/working-hours-management.component";
import {DocumentEditComponent} from "./main-ui/document-management/document-edit/document-edit.component";

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {path: 'dashboard', component: DashboardComponent},
  {path: 'customer-management', component: CustomerManagementComponent},
  {path: 'income-management', component: IncomeManagementComponent},
  {path: 'settings', component: SettingsComponent},
  {path: 'todo-management', component: TodoManagementComponent},
  {path: 'working-hours-management', component: WorkingHoursManagementComponent},
  {path: 'project-management', component: ProjectManagementComponent},
  {path: 'documents', component: DocumentManagementComponent},
  {path: 'documents/edit/:id', component: DocumentEditComponent}
];
