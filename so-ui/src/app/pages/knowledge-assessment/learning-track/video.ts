export class Videos{
    id:number;
    videoUrl:string;
    playHead:number;
    videoDesc:string;
}

export const VIDEOS: Videos[] = [
    { id: 1, videoUrl:'http://35.211.27.99:9000/albums/video1.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=priya%2F20190716%2F%2Fs3%2Faws4_request&X-Amz-Date=20190716T105501Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=33a3570c2aa060826ec56d134003651910eb7842ff6d9d7d3f9d5bd655117d5d',playHead:5,videoDesc:"Create Load Flow with ETAP"},
    { id: 2, videoUrl:'http://35.211.27.99:9000/albums/video2.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=priya%2F20190716%2F%2Fs3%2Faws4_request&X-Amz-Date=20190716T105601Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=c976cead9b4d76a9bc4850ba78f47a22199c1b2fd12559870ad1aab1952606cf',playHead:50,videoDesc:"Create Load Flow ETAP - Part 2"},
    { id: 3, videoUrl:'http://35.211.27.99:9000/albums/video3.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=priya%2F20190716%2F%2Fs3%2Faws4_request&X-Amz-Date=20190716T105616Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=085ac81b3a6bf09db737172781710f88d1d504d99e7b07f3f820cdfc0adcdd41',playHead:25,videoDesc:"Unsymmetrical Fault Power System Analysis"},
    { id: 4, videoUrl:'http://35.211.27.99:9000/albums/video4.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=priya%2F20190716%2F%2Fs3%2Faws4_request&X-Amz-Date=20190716T105637Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=e597e13c7b97782cc7876078add88c4936ffd64916554542879f97b340f2293f',playHead:10,videoDesc:"Power Transmission & Distribution"},
    { id: 5, videoUrl:'http://35.211.27.99:9000/albums/video5.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=priya%2F20190716%2F%2Fs3%2Faws4_request&X-Amz-Date=20190716T105702Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=3964f9293503e967c8ac72fb44f759b52aeba2b72994a1bea95ad8893937b94a',playHead:90,videoDesc:"Principles of Symmetrical Components"},
    { id: 6, videoUrl:'http://35.211.27.99:9000/albums/video6.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=priya%2F20190716%2F%2Fs3%2Faws4_request&X-Amz-Date=20190716T105718Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=716952165ed91542bc1a658d34ed489755d6b048441db69bcdca0078afeec75c',playHead:25,videoDesc:"Protection relay_ Power system protection"},
    { id: 7, videoUrl:'http://35.211.27.99:9000/albums/video10.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=priya%2F20190716%2F%2Fs3%2Faws4_request&X-Amz-Date=20190716T105744Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=a76b9e4cfc2327cdd8ff524fd53b3952ca041231a3a7e41452c5c78b89fe85c8',playHead:null,videoDesc:"How Relay works..."},
    { id: 8, videoUrl:'http://35.211.27.99:9000/albums/video8.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=priya%2F20190716%2F%2Fs3%2Faws4_request&X-Amz-Date=20190716T105808Z&X-Amz-Expires=432000&X-Amz-SignedHeaders=host&X-Amz-Signature=efa0ecfd7cf218f4e66729c5a90e2c7a6604a7aa7e9d9bb2a22388b135806571',playHead:50,videoDesc:"Load Flow Calculation in ETAP"}

]