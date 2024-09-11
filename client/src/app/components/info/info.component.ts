import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

const githubIcon = 'assets/socialIcons/github.svg';
const linkedinIcon = 'assets/socialIcons/linkedin.svg';
const gmailIcon = 'assets/socialIcons/gmail.svg';

@Component({
  selector: 'app-info',
  standalone: true,
  imports: [],
  templateUrl: './info.component.html',
  styleUrl: './info.component.scss',
})
export class InfoComponent {
  constructor(private ref: MatDialogRef<InfoComponent>) {
    ref.backdropClick().subscribe(() => {
      ref.close();
    });
  }

  socialLinks = [
    {
      id: 1,
      name: 'github',
      img: githubIcon,
      link: 'https://github.com/will9191',
    },
    {
      id: 2,
      name: 'linkedin',
      img: linkedinIcon,
      link: 'https://www.linkedin.com/in/will9191/',
    },
    {
      id: 3,
      name: 'gmail',
      img: gmailIcon,
      link: 'mailto:willian.contato91@gmail.com?subject=Olá!',
    },
  ];
}
