class Solution {
public:
    bool checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest point inside or on the rectangle to the circle's center
        int nearestX = std::max(x1, std::min(xCenter, x2));
        int nearestY = std::max(y1, std::min(yCenter, y2));
        
        // Calculate squared distance between the circle center and the closest point
        int dx = nearestX - xCenter;
        int dy = nearestY - yCenter;
        int distSq = dx * dx + dy * dy;
        
        return distSq <= radius * radius;
    }
};