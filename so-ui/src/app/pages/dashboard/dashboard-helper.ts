export default class DashboardHelper {

    static getIcons(status) {
        let d;
        switch (status) {
            case 'TAR-N1-APPROVAL':
                d = 'fa-plane';
                break;
            case 'TAR-ACC2-APPROVAL':
                d = 'fa-rocket';
                break;
            case 'LR-N1-APPROVAL':
                d = 'fa-calendar-times-o';
                break;
            case 'LR-N2-APPROVAL':
                d = 'fa-calendar-minus-o';
                break;
            case 'LR-SETTLEMENT':
                d = 'fa-calendar-check-o';
                break;
            case 'ECR-ACC2-APPROVAL':
                d = 'fa-credit-card-alt';
                break;
            case 'ECR-SETTLEMENT':
                d = 'fa-usd';
                break;
            case 'ECR-N1-APPROVAL':
                d = 'fa-credit-card';
                break;
            case 'ECR-N2-APPROVAL':
                d = 'fa-credit-card';
                break;
            case 'PENDING-APPROVAL-RECRUIT':
                d = 'fa-university';
                break;
            case 'PENDING-APPROVAL-TRAINING':
                d = 'fa-graduation-cap';
                break;
            default:
                d = 'fa-desktop';
        }
        return d;
    }

}